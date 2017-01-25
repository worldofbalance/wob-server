package shared.model;

public class NewsArticle {

    private int article_id;
    private String text;
    private String create_time;

    public NewsArticle(int article_id) {
        this.article_id = article_id;
    }

    public int getID() {
        return article_id;
    }

    public String getText() {
        return text;
    }

    public String setText(String text) {
        return this.text = text;
    }

    public String getCreateTime() {
        return create_time;
    }

    public String setCreateTime(String create_time) {
        return this.create_time = create_time;
    }
}
