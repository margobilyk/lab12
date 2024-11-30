package ua.ucu.edu.apps.task1;

public class ATM {
    public Section section;
    public ATM(){
        this.section = new Section_500();
        Section_200 section_200 = new Section_200();
        section.setNext(section_200);
    }
    public void giveMoney(int amount){
        section.process(amount);
    }
}
