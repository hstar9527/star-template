package com.star.util;


/**
 * @author star
 * @version 1.0.0
 * @description
 */
public final class Token {

    public static final int UNKNOWN_TYPE = -1;

    private final String message;

    private final int offset;

    private final int type;

    /**
     * Create Token.
     *
     * @param message message
     * @param offset  offset
     */
    public Token(String message, int offset) {
        this(message, offset, UNKNOWN_TYPE);
    }

    /**
     * Create Token with type.
     *
     * @param message message
     * @param offset  offset
     * @param type    type
     */
    public Token(String message, int offset, int type) {
        if (StringUtils.isEmpty(message))
            throw new IllegalArgumentException("message == null");
        this.message = message;
        this.offset = offset;
        this.type = type;
    }

    /**
     * Get message.
     *
     * @return message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Get offset.
     *
     * @return offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Get type.
     *
     * @return type.
     */
    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + offset;
        result = prime * result + type;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Token other = (Token) obj;
        if (message == null) {
            if (other.message != null) return false;
        } else if (!message.equals(other.message)) return false;
        if (offset != other.offset) return false;
        if (type != other.type) return false;
        return true;
    }

}