package info.kimjihyok.new_york_times_client.post.list;

import java.util.List;

import info.kimjihyok.new_york_times_client.db.PostItem;

/**
 * Created by jkimab on 2016. 12. 5..
 */

public class TopStoryResult {
    public String status;
    public String copyright;
    public String section;
    public String last_updated;
    public String num_results;
    public List<PostItem> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public String getNum_results() {
        return num_results;
    }

    public void setNum_results(String num_results) {
        this.num_results = num_results;
    }

    public List<PostItem> getResults() {
        return results;
    }

    public void setResults(List<PostItem> results) {
        this.results = results;
    }
}
