package com.jshc.waveprogressbar.activitys;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jshc.waveprogressbar.ItemDecorations.CardsItemDecoration;
import com.jshc.waveprogressbar.R;
import com.jshc.waveprogressbar.adapters.CardsAdapter;
import com.jshc.waveprogressbar.beans.CardBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardsActivity extends BaseActivity {
    @BindView(R.id.cards_recyclerView)
    RecyclerView cardsRecyclerView;

    private CardsAdapter cardsAdapter;
    private List<CardBean> cardBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        cardBeanList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CardBean bean = new CardBean(i, "", "");
            cardBeanList.add(bean);
        }
        cardsAdapter = new CardsAdapter(this, cardBeanList);
        cardsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        cardsRecyclerView.addItemDecoration(new CardsItemDecoration(-100));
        cardsRecyclerView.setAdapter(cardsAdapter);
    }


}
