public class Date {
    private int day, year;
    private String month;
    public Date(int day, String month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }


    public String toString(){
        return month + day + ", " + year;
    }
}
