package com.example.myapplication;

//import android.os.Bundle;
//import com.example.statemanagercon.ResourceTable;

import com.example.myapplication.state.LoadingState;
import com.example.mylibrary.StateLayout;
import com.example.mylibrary.state.CoreState;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;
import ohos.app.Context;
import org.jetbrains.annotations.Nullable;

public class RecyclerViewActivity extends Ability {

    @Override
    protected void onStart(@Nullable Intent savedInstanceState) {
        super.onStart(savedInstanceState);
        setUIContent(ResourceTable.Layout_ability_main);

        ListContainer recyclerView = (ListContainer) findComponentById(ResourceTable.Id_text_helloworld);
        recyclerView.setLayoutManager(new DirectionalLayoutManager());
        recyclerView.setReboundEffectParams(new ListContainer.ReboundEffectParams(0,0,0));

    }


    public class Adapter {


        public ListContainer onCreateViewHolder(ComponentContainer parent, int viewType) {
            ListContainer viewHolder = null;
            if (viewType == 0) {
                viewHolder = new DefaultViewHolder(
                        (ComponentContainer) LayoutScatter.getInstance(RecyclerViewActivity.this).parse(ResourceTable.Layout_default_viewholder,parent,false));
            } else {
                viewHolder = new StateManagerViewHolder(
                        (ComponentContainer) LayoutScatter.getInstance(RecyclerViewActivity.this).parse(ResourceTable.Layout_statemanager_viewholder, parent, false));
            }
            return viewHolder;
        }

        public void onBindViewHolder(ListContainer holder, int position) {
            if (holder instanceof DefaultViewHolder) {
                ((DefaultViewHolder) holder).textView.setText("这是第" + (position + 1) + "个Item");
            } else if (holder instanceof StateManagerViewHolder) {
                ((StateManagerViewHolder) holder).stateLayout.showState(LoadingState.STATE);
                ((StateManagerViewHolder) holder).showCoreState();
            }
        }

        public int getItemCount() {
            return 20;
        }


        public int getItemViewType(int position) {
            return position == 9 ? 1 : 0;
        }

    }


    public class StateManagerViewHolder extends ListContainer {
        StateLayout stateLayout;

        public StateManagerViewHolder(ComponentContainer itemView) {
            super((Context)itemView);
            stateLayout = (StateLayout) itemView.findComponentById(ResourceTable.Id_text_helloworld);
        }

        public void showCoreState() {
            StateLayout itemView = null;
            itemView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    stateLayout.showState(CoreState.STATE);
                }
            }, 3000);
        }
    }

    public class DefaultViewHolder extends ListContainer {
        Text textView;

        public DefaultViewHolder(ComponentContainer itemView) {
            super((Context) itemView);
            textView = (Text)itemView.findComponentById(ResourceTable.Id_text_helloworld);
        }
    }
}
