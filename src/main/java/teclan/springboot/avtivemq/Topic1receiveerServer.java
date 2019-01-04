package teclan.springboot.avtivemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import teclan.springboot.exception.UnImplementException;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @ClassName: Queue1SenderServer
 * @Description: TODO
 * @Author: Teclan
 * @Date: 2019/1/4 10:31
 **/
@Service
public class Topic1receiveerServer extends AbstractActiveMQProDuceService  {

    private static final String TOPIC_NAME="topic1";


    @Resource(name=TOPIC_NAME)
    private Topic topic;

    @Override
    protected  String getTopicName(){
        return TOPIC_NAME;
    }

    @Override
    protected Topic getTopic() {
        return topic;
    }

//    /**
//     * 接收消息
//     *
//     * @param message
//     */
//    @JmsListener(destination = TOPIC_NAME)
//    @Override
//    public boolean receiveTopicMsg(String message) throws UnImplementException  {
//       return receiveTopicMsg(getTopicName(),message);
//    }

}
