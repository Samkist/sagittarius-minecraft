package dev.samkist.lumae.sagittarius.data.models;

public class JoinLeaveFormatBuilder extends FormatBuilder<JoinLeaveFormat, JoinLeaveFormatBuilder> {

    public JoinLeaveFormatBuilder(JoinLeaveFormat format) {
        super(format);
    }

    public JoinLeaveFormatBuilder() {

    }

    @Override
    public JoinLeaveFormat build() {
        return new JoinLeaveFormat(id(), "join-leave-formats", permission(), formatStrings(), priority());
    }
}
