package kristiania;

public class Photographer {
    private int yearsOfExperience;
    private boolean freelance = false;


    public void shootPhotos() {
        System.out.println("Snaps snaps!");
    }

    public Photographer(int yearsOfExperience, boolean freelance) {
        this.yearsOfExperience = yearsOfExperience;
        this.freelance = freelance;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public boolean isFreelance() {
        return freelance;
    }

    public void setFreelance(boolean freelance) {
        this.freelance = freelance;
    }
}
