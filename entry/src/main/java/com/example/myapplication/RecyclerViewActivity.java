package com.example.myapplication;


import com.example.myapplication.state.ExceptionState;
import com.example.myapplication.state.LoadingState;
import com.example.mylibrary.StateLayout;
import com.example.mylibrary.state.CoreState;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;
import ohos.app.Context;
import ohos.eventhandler.EventHandler;
import ohos.eventhandler.EventRunner;
import org.jetbrains.annotations.Nullable;

public class RecyclerViewActivity extends Ability {

    @Override
    protected void onStart(@Nullable Intent savedInstanceState) {
        super.onStart(savedInstanceState);
        super.setUIContent(ResourceTable.Layout_activity_recyclerview_demo);

        ListContainer recyclerView = (ListContainer) findComponentById(ResourceTable.Id_recyclerview);
        Adapter listprovider = new Adapter();
        recyclerView.setItemProvider(listprovider);
        recyclerView.setItemClickedListener(new ListContainer.ItemClickedListener() {
            @Override
            public void onItemClicked(ListContainer holder, Component component, int position, long l) {
                //This method is empty because there is no woking for we it, bu twe need Listcontainer.ItemClickedListener().
            }
        });

    }


    public class Adapter extends BaseItemProvider{

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public long getItemId(int position) {
        return position == 9 ? 1 : 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public Component getComponent(int viewType, Component component, ComponentContainer parent) {
        Component viewHolder = null;
        if (viewType == 9) {
            viewHolder =  LayoutScatter.getInstance(RecyclerViewActivity.this).parse(ResourceTable.Layout_statemanager_viewholder, parent, false);
//            StateLayout stateLayout = (StateLayout) viewHolder.findComponentById(ResourceTable.Id_statelayout);
//            stateLayout.showState(LoadingState.STATE);

         } else {
            viewHolder = LayoutScatter.getInstance(RecyclerViewActivity.this).parse(ResourceTable.Layout_default_viewholder, parent, false);
            Text text = (Text) viewHolder.findComponentById(ResourceTable.Id_textview);
            text.setText("This is the first " + (viewType + 1) + "Piece  Item");
        }

        return viewHolder; 
    }

        public class StateManagerViewHolder extends Component{
            StateLayout stateLayout;

            public StateManagerViewHolder(ComponentContainer itemView) {
                super((Context) itemView);
                stateLayout = (StateLayout) itemView.findComponentById(ResourceTable.Id_statelayout);
            }

            public void showCoreState() {
                EventHandler eventhandler = new EventHandler(EventRunner.getMainEventRunner());
                eventhandler.postTask(() -> stateLayout.showState(CoreState.STATE), 3000);
            }
        }

        public class DefaultViewHolder extends Component {
            Text textView;

            public DefaultViewHolder(ComponentContainer itemView) {
                super((Context) itemView);
                textView = (Text) itemView.findComponentById(ResourceTable.Id_textview);
            }
        }
    }}
