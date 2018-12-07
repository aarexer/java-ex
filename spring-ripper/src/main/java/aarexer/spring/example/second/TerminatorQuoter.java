package aarexer.spring.example.second;

import aarexer.spring.example.Quoter;

@DeprecatedAndUseImpl(newImpl = TerminatorQuoter2.class)
public class TerminatorQuoter implements Quoter {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void quote() {
        System.out.println("Quote: " + message);
    }
}
