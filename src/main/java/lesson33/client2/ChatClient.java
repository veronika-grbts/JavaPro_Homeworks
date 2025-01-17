package lesson33.client2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class ChatClient {
    private static final Logger logger = LogManager.getLogger(ChatClient.class);
    private static final int PORT = 8080;
    private static final String HOST = "127.0.0.1";
    static String input;
    static Channel channel;

    public void start (Scanner scanner, String username) {
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new StringDecoder());
                            p.addLast(new StringEncoder());
                            p.addLast(new ClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect(HOST, PORT).sync();
            while (scanner.hasNext()) {
                input = scanner.nextLine();
                if(input.equals("exit")){
                    System.exit(0);
                }
                channel = future.sync().channel();
                channel.writeAndFlush("["+ username + "] "+ input);
                channel.flush();
            }
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("An error occurred: " + e.getMessage());
            Thread.currentThread().interrupt();
        } finally {
            logger.info("Shutting down client...");
            group.shutdownGracefully();
        }
    }
}
