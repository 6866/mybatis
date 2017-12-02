package entity;

/**
 * Created by yangxingrom on 2017/11/26.
 */
public class Pages {
    private int sumNumber;//总条数
    private int currentPage;//当前第几页
    private int sumPage;//总页数
    private int pageNumber = 5;//每页显示条数
    private int sqlIndex;//对应数据库第几条开始
    private int sqlNumber;//对应数据库每页条数

    public void count(){
        //计算总页数
        this.sumPage = this.sumNumber/this.pageNumber;
        int page = (this.sumNumber % this.pageNumber) == 0 ? 0 : 1;
        this.sumPage = sumPage + page;
        if(this.sumPage <= 0)
            this.sumPage = 1;//当总页数等于0时，设值为1
        //设置当前页
        if(this.currentPage <=0 )
            this.currentPage = 1;

        //设置sqlindex
        this.sqlIndex = (this.currentPage - 1) * this.pageNumber;
        this.sqlNumber = this.pageNumber;

    }
    public int getSumNumber() {
        return sumNumber;
    }

    public void setSumNumber(int sumNumber) {
        this.sumNumber = sumNumber;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getSumPage() {
        return sumPage;
    }

    public void setSumPage(int sumPage) {
        this.sumPage = sumPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getSqlIndex() {
        return sqlIndex;
    }

    public void setSqlIndex(int sqlIndex) {
        this.sqlIndex = sqlIndex;
    }

    public int getSqlNumber() {
        return sqlNumber;
    }

    public void setSqlNumber(int sqlNumber) {
        this.sqlNumber = sqlNumber;
    }
}
