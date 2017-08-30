public class Update {
    private String commentID;
    private String author;
    private String timestamp;
    private String threadID;

    Update(String commentID, String author, String timestamp, String threadID) {
        this.commentID = commentID;
        this.author = author;
        this.timestamp = timestamp;
        this.threadID = threadID;
    }


    private String getCommentID() {
        return commentID;
    }

    String getAuthor() {
        return author;
    }

    String getTimestamp() {
        return timestamp;
    }

    private String getThreadID() {
        return threadID;
    }

    @Override
    public String toString() {
        return getAuthor() + "," + getTimestamp() + "," + getCommentID() + "," + getThreadID();
    }
}
