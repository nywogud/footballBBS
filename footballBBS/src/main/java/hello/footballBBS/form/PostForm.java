package hello.footballBBS.form;

public class PostForm {

    private String writer;
    private String title;
    private String content;
    private Long contentNumber;

    public Long getContentNumber() {
        return contentNumber;
    }

    public void setContentNumber(Long contentNumber) {
        this.contentNumber = contentNumber;
    }

        public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
