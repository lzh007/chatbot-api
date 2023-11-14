package cn.chatbot.api.domain.ai;

import java.io.IOException;

/**
 *  ChatGPT open ai 接口 https://platform.openai.com/api-keys
 */

public interface IOpenAI {

    String doChatGPT(String  question) throws IOException;
}
