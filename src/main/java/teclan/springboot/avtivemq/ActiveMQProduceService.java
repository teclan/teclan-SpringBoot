package teclan.springboot.avtivemq;

import teclan.springboot.exception.UnImplementException;

/**
 * @ClassName: ActiveMQProduceService
 * @Description: TODO
 * @Author: Teclan
 * @Date: 2019/1/4 10:32
 **/
public interface ActiveMQProduceService {

    /**
     * 发送消息
     * @param message
     */
    public boolean sendQueueMsg(String message);

    /**
     * 接收消息
     * @param message
     */
    public boolean receiveQueueMsg(String message) throws UnImplementException;


    /**
     * 发送消息
     * @param message
     */
    public boolean sendTopicMsg(String message);

    /**
     * 接收消息
     * @param message
     */
    public boolean receiveTopicMsg(String message) throws UnImplementException;
}
