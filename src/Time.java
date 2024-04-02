import java.util.Objects;

public class Time implements Comparable<Time> {
    private int day;
    private int month;
    private int year;
    private int hours;
    private int minutes;

    public Time(int day, int month, int year, int hours, int minutes) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date");
        }
        if (!isValidTime(hours, minutes)) {
            throw new IllegalArgumentException("Invalid time");
        }

        this.day = day;
        this.month = month;
        this.year = year;
        this.hours = hours;
        this.minutes = minutes;
    }

    private boolean isValidDate(int day, int month, int year) {
        if (year < 0 || month < 1 || month > 12) {
            return false;
        }
        int maxDay = 31;
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            maxDay = 30;
        } else if (month == 2) {
            maxDay = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 29 : 28;
        }
        return day >= 1 && day <= maxDay;
    }

    private boolean isValidTime(int hours, int minutes) {
        return hours >= 0 && hours < 24 && minutes >= 0 && minutes < 60;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date");
        }
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date");
        }
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date");
        }
        this.year = year;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        if (!isValidTime(hours, minutes)) {
            throw new IllegalArgumentException("Invalid time");
        }
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        if (!isValidTime(hours, minutes)) {
            throw new IllegalArgumentException("Invalid time");
        }
        this.minutes = minutes;
    }

    public Time calculateLandingTime(int flightHours, int flightMinutes) {
        int totalMinutes = hours * 60 + minutes + flightHours * 60 + flightMinutes;
        int landingDay = day;
        int landingMonth = month;
        int landingYear = year;
        int landingHours = totalMinutes / 60;
        int landingMinutes = totalMinutes % 60;

        while (landingHours >= 24) {
            landingHours -= 24;
            landingDay++;
            if (!isValidDate(landingDay, landingMonth, landingYear)) {
                landingDay = 1;
                landingMonth++;
                if (landingMonth > 12) {
                    landingMonth = 1;
                    landingYear++;
                }
            }
        }

        return new Time(landingDay, landingMonth, landingYear, landingHours, landingMinutes);
    }

    @Override
    public int compareTo(Time otherTime) {
        if (this.year != otherTime.year) {
            return Integer.compare(this.year, otherTime.year);
        }
        if (this.month != otherTime.month) {
            return Integer.compare(this.month, otherTime.month);
        }
        if (this.day != otherTime.day) {
            return Integer.compare(this.day, otherTime.day);
        }
        if (this.hours != otherTime.hours) {
            return Integer.compare(this.hours, otherTime.hours);
        }
        return Integer.compare(this.minutes, otherTime.minutes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return day == time.day &&
                month == time.month &&
                year == time.year &&
                hours == time.hours &&
                minutes == time.minutes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year, hours, minutes);
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d %02d:%02d", day, month, year, hours, minutes);
    }



    public static void main(String[] args) {
        Time takeOff = new Time(30, 4, 2024, 10, 30);
        Time landing = takeOff.calculateLandingTime(24, 30);
        System.out.println("Take-off time: " + takeOff);
        System.out.println("Landing time: " + landing);
    }
}