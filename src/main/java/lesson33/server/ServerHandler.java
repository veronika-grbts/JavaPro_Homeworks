package lesson33.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServerHandler extends SimpleChannelInboundHandler<String> {

    private static final Logger logger = LogManager.getLogger(ServerHandler.class);
    private static final SimpleDateFormat DATE  = new SimpleDateFormat("HH:mm:ss");
    static final List<Channel> channels = new ArrayList<>();

    public void channelActive(final ChannelHandlerContext ctx) {
        logger.info("Client connected: " + ctx);
        channels.add(ctx.channel());
    }
    @Override
    public void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        if(s.equals("exit")){
            logger.info("Client disconnected: " + channelHandlerContext);
            for(Channel channel : channels){
                channel.writeAndFlush(s);
            }
            channelHandlerContext.close();
        } else {
            String time = DATE.format(new Date());
            logger.info("Received message: [ "+time+" ] Client - " +  s);
            for (Channel channel : channels) {
                channel.writeAndFlush("[ "+ time+ "]" + s);
            }
        }

    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("Client disconnected: "+ ctx);
        channels.remove(ctx.channel());
        ctx.close();
    }
}
