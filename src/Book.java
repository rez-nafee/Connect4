public class Book{
    private String title;
    private String author;
    private String tableOfContents = "";
    private int nextPage = 1;

    public Book (String bk, String name){
        this.title = bk;
        this.author = name;
    }

    public void addChapter(String chapter, int pages){
        tableOfContents += "\n" + chapter + "..." + this.nextPage;
        this.nextPage += pages;
    }

    public int getPages(){
        return this.nextPage;
    }

    public String getTableOfContents(){
        return this.tableOfContents;
    }

    public String toString(){
        return this.title + "\n" + this.author;
    }
}