package aarexer.spring.example.first;

import aarexer.spring.example.Quoter;

import javax.annotation.PostConstruct;

@Profiling
public class TerminatorQuoter implements Quoter {
    @InjectRandomInt(min = 0, max = 10)
    private int repeat;

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    @PostConstruct
    public void init() {
        System.out.println(repeat);
    }

    @Override
    public void quote() {
        for (int i = 0; i < repeat; i++) {
            System.out.println("Quote: " + message);
        }
    }
}
