public class TimeFormat {
    int seconds;
    int minutes;
    int hours;
    int days;

    TimeFormat(int seconds,int minutes,int hours,int days){
        this.seconds = seconds;
        this.minutes = minutes;
        this.hours = hours;
        this.days = days;
    }

    @Override
    public String toString() {
        return days+" days "+hours+" hours "+minutes+" minutes "+seconds+" seconds ";
    }
}
