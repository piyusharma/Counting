/**
 * Created by Blizzard on 05-Aug-17.
 */
public class Update {
    String commentID;
    String author;
    String timestamp;
    String threadID;

    public Update(String commentID, String author, String timestamp, String threadID) {
        this.commentID = commentID;
        this.author = author;
        this.timestamp = timestamp;
        this.threadID = threadID;
    }


    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getThreadID() {
        return threadID;
    }

    public void setThreadID(String threadID) {
        this.threadID = threadID;
    }


    @Override
    public String toString() {
        return getAuthor() + "," + getTimestamp() + "," + getCommentID() + "," + getThreadID();
    }
}
