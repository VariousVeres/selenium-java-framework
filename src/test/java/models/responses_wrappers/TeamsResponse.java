package models.responses_wrappers;

import models.team.TeamListItem;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class TeamsResponse {

    private Map<String, String> links;
    private int count;
    private List<TeamListItem> results;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamsResponse)) return false;
        TeamsResponse that = (TeamsResponse) o;
        return count == that.count &&
                Objects.equals(links, that.links) &&
                Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(links, count, results);
    }

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(Map<String, String> links) {
        this.links = links;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<TeamListItem> getResults() {
        return results;
    }

    public void setResults(List<TeamListItem> results) {
        this.results = results;
    }


}
