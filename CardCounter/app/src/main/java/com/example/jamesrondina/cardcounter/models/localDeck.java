package com.example.jamesrondina.cardcounter.models;

import com.example.jamesrondina.cardcounter.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by jamesrondina on 9/10/16.
 *
 * Blackjack doesn't really need an API, but I had to include one as part of the grading requirements
 * This class is for the offline component, which makes the app better and is more fun to code than
 * making API calls all day in my opinion
 */
public class LocalDeck {

    List<Card> cards = new ArrayList<>();
    int index = 0;

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void remove(){
        cards.remove(0);
    }

    public int size() {
        return cards.size();
    }


    public void shuffle() {
        //shuffles all the cards using the Fisher-Yates method
        // see: https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
        //goes through each card and swaps it with another random card later in the deck
        //doing it this way is way more fun than relying on the API ;-)

        Random random = new Random();
        random.nextInt();

        for (int i = 0; i < cards.size(); i++) {
            int move = i + random.nextInt(cards.size() - i);
            Card swap = cards.get(i); //use swap as placeholder for card at index
            cards.set(i, cards.get(move)); //put card from the move position into the index
            cards.set(move, swap); //put swap card from original index position where the moved card once was
        }

    }

    public void loadCards() {

        //loading 52 unique card objects into a LocalDeck
        //don't worry, I didn't do this line by line, I copy pasted and then did find/replace all
        
        //hearts

        Card h2 = new Card();
        Card h3 = new Card();
        Card h4 = new Card();
        Card h5 = new Card();
        Card h6 = new Card();
        Card h7 = new Card();
        Card h8 = new Card();
        Card h9 = new Card();
        Card h10 = new Card();
        Card hj = new Card();
        Card hq = new Card();
        Card hk = new Card();
        Card ha = new Card();

        h2.setBjackVal(2);
        h3.setBjackVal(3);
        h4.setBjackVal(4);
        h5.setBjackVal(5);
        h6.setBjackVal(6);
        h7.setBjackVal(7);
        h8.setBjackVal(8);
        h9.setBjackVal(9);
        h10.setBjackVal(10);
        hj.setBjackVal(10);
        hq.setBjackVal(10);
        hk.setBjackVal(10);
        ha.setBjackVal(11);

        h2.setResId(R.mipmap.h2);
        h3.setResId(R.mipmap.h3);
        h4.setResId(R.mipmap.h4);
        h5.setResId(R.mipmap.h5);
        h6.setResId(R.mipmap.h6);
        h7.setResId(R.mipmap.h7);
        h8.setResId(R.mipmap.h8);
        h9.setResId(R.mipmap.h9);
        h10.setResId(R.mipmap.h0);
        hj.setResId(R.mipmap.jh);
        hq.setResId(R.mipmap.qh);
        hk.setResId(R.mipmap.kh);
        ha.setResId(R.mipmap.ah);
        
        cards.add(h2);
        cards.add(h3);
        cards.add(h4);
        cards.add(h5);
        cards.add(h6);
        cards.add(h7);
        cards.add(h8);
        cards.add(h9);
        cards.add(h10);
        cards.add(hj);
        cards.add(hq);
        cards.add(hk);
        cards.add(ha);
        
        //diamonds

        Card d2 = new Card();
        Card d3 = new Card();
        Card d4 = new Card();
        Card d5 = new Card();
        Card d6 = new Card();
        Card d7 = new Card();
        Card d8 = new Card();
        Card d9 = new Card();
        Card d10 = new Card();
        Card dj = new Card();
        Card dq = new Card();
        Card dk = new Card();
        Card da = new Card();

        d2.setBjackVal(2);
        d3.setBjackVal(3);
        d4.setBjackVal(4);
        d5.setBjackVal(5);
        d6.setBjackVal(6);
        d7.setBjackVal(7);
        d8.setBjackVal(8);
        d9.setBjackVal(9);
        d10.setBjackVal(10);
        dj.setBjackVal(10);
        dq.setBjackVal(10);
        dk.setBjackVal(10);
        da.setBjackVal(11);

        d2.setResId(R.mipmap.d2);
        d3.setResId(R.mipmap.d3);
        d4.setResId(R.mipmap.d4);
        d5.setResId(R.mipmap.d5);
        d6.setResId(R.mipmap.d6);
        d7.setResId(R.mipmap.d7);
        d8.setResId(R.mipmap.d8);
        d9.setResId(R.mipmap.d9);
        d10.setResId(R.mipmap.d0);
        dj.setResId(R.mipmap.jd);
        dq.setResId(R.mipmap.qd);
        dk.setResId(R.mipmap.kd);
        da.setResId(R.mipmap.ad);

        cards.add(d2);
        cards.add(d3);
        cards.add(d4);
        cards.add(d5);
        cards.add(d6);
        cards.add(d7);
        cards.add(d8);
        cards.add(d9);
        cards.add(d10);
        cards.add(dj);
        cards.add(dq);
        cards.add(dk);
        cards.add(da);

        //clubs

        Card c2 = new Card();
        Card c3 = new Card();
        Card c4 = new Card();
        Card c5 = new Card();
        Card c6 = new Card();
        Card c7 = new Card();
        Card c8 = new Card();
        Card c9 = new Card();
        Card c10 = new Card();
        Card cj = new Card();
        Card cq = new Card();
        Card ck = new Card();
        Card ca = new Card();

        c2.setBjackVal(2);
        c3.setBjackVal(3);
        c4.setBjackVal(4);
        c5.setBjackVal(5);
        c6.setBjackVal(6);
        c7.setBjackVal(7);
        c8.setBjackVal(8);
        c9.setBjackVal(9);
        c10.setBjackVal(10);
        cj.setBjackVal(10);
        cq.setBjackVal(10);
        ck.setBjackVal(10);
        ca.setBjackVal(11);

        c2.setResId(R.mipmap.c2);
        c3.setResId(R.mipmap.c3);
        c4.setResId(R.mipmap.c4);
        c5.setResId(R.mipmap.c5);
        c6.setResId(R.mipmap.c6);
        c7.setResId(R.mipmap.c7);
        c8.setResId(R.mipmap.c8);
        c9.setResId(R.mipmap.c9);
        c10.setResId(R.mipmap.c0);
        cj.setResId(R.mipmap.jc);
        cq.setResId(R.mipmap.qc);
        ck.setResId(R.mipmap.kc);
        ca.setResId(R.mipmap.ac);

        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
        cards.add(c6);
        cards.add(c7);
        cards.add(c8);
        cards.add(c9);
        cards.add(c10);
        cards.add(cj);
        cards.add(cq);
        cards.add(ck);
        cards.add(ca);

        //spades

        Card s2 = new Card();
        Card s3 = new Card();
        Card s4 = new Card();
        Card s5 = new Card();
        Card s6 = new Card();
        Card s7 = new Card();
        Card s8 = new Card();
        Card s9 = new Card();
        Card s10 = new Card();
        Card sj = new Card();
        Card sq = new Card();
        Card sk = new Card();
        Card sa = new Card();

        s2.setBjackVal(2);
        s3.setBjackVal(3);
        s4.setBjackVal(4);
        s5.setBjackVal(5);
        s6.setBjackVal(6);
        s7.setBjackVal(7);
        s8.setBjackVal(8);
        s9.setBjackVal(9);
        s10.setBjackVal(10);
        sj.setBjackVal(10);
        sq.setBjackVal(10);
        sk.setBjackVal(10);
        sa.setBjackVal(11);

        s2.setResId(R.mipmap.s2);
        s3.setResId(R.mipmap.s3);
        s4.setResId(R.mipmap.s4);
        s5.setResId(R.mipmap.s5);
        s6.setResId(R.mipmap.s6);
        s7.setResId(R.mipmap.s7);
        s8.setResId(R.mipmap.s8);
        s9.setResId(R.mipmap.s9);
        s10.setResId(R.mipmap.s0);
        sj.setResId(R.mipmap.js);
        sq.setResId(R.mipmap.qs);
        sk.setResId(R.mipmap.ks);
        sa.setResId(R.mipmap.as);

        cards.add(s2);
        cards.add(s3);
        cards.add(s4);
        cards.add(s5);
        cards.add(s6);
        cards.add(s7);
        cards.add(s8);
        cards.add(s9);
        cards.add(s10);
        cards.add(sj);
        cards.add(sq);
        cards.add(sk);
        cards.add(sa);


    }


}
