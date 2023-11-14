package cn.chatbot.api.domain.zsxq.model.res;

import cn.chatbot.api.domain.zsxq.model.vo.Topic;

import java.util.List;

public class RespData {
    private List<Topic> topics;

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
