package dev.samkist.lumae.sagittarius.data.models;

public class ChatFormatBuilder extends FormatBuilder<ChatFormat, ChatFormatBuilder> {

    public ChatFormatBuilder(ChatFormat format) {
        super(format);
    }

    public ChatFormatBuilder() {
        super();
    }

    @Override
    public ChatFormat build() {
        return new ChatFormat(id(), scope(), permission(), formatStrings(), priority());
    }
}
