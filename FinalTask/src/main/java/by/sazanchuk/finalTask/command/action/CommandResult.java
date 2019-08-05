package by.sazanchuk.finalTask.command.action;

import java.util.Objects;

public class CommandResult {
    private String page;
    private boolean isRedirect;

    public CommandResult(String page, boolean isRedirect) {
        this.page = page;
        this.isRedirect = isRedirect;
    }

    public CommandResult(String page) {
        this.page = page;
    }

    public CommandResult(){}

    public String getPage() {
        return page;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setRedirect(boolean redirect) {
        isRedirect = redirect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandResult that = (CommandResult) o;
        return isRedirect == that.isRedirect() &&
                Objects.equals(getPage(), that.getPage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPage(), isRedirect());
    }
}
