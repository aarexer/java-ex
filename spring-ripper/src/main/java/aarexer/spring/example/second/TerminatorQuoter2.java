package aarexer.spring.example.second;

import aarexer.spring.example.Quoter;

public class TerminatorQuoter2 extends TerminatorQuoter implements Quoter {
    @Override
    public void quote() {
        System.out.println("New Implementation for TerminatorQuoter!");
    }
}
