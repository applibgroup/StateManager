package com.example.myapplication.state;



import com.example.myapplication.ResourceTable;
import com.example.mylibrary.state.BaseState;
import com.example.mylibrary.state.StateProperty;
import ohos.agp.components.Component;
import ohos.agp.components.Text;



public class BizState extends BaseState <BizState.BizMo> {
    public final static String BIZ_STATE = "BizState";
    private Text title;//TextView->Text getting Component
    private Text desc;
    private Text content;

    @Override
    protected int getLayoutId() {

       // return R.layout.biz_state;
        return com.example.myapplication.ResourceTable.Layout_biz_state;


    }

    @Override
    protected void onViewCreated(Component stateView) {
        title = (Text) stateView.findComponentById(ResourceTable.Id_title);
        desc = (Text) stateView.findComponentById(ResourceTable.Id_desc);
        content = (Text) stateView.findComponentById(ResourceTable.Id_content);
    }

    @Override
    public void setViewProperty(BizMo bizMo) {
        super.setViewProperty(bizMo);

        title.setText(bizMo.title);

        desc.setText(bizMo.desc);

        content.setText(bizMo.content);
    }

    @Override
    public String getState() {
        return BIZ_STATE;
    }



    public static class BizMo implements StateProperty {
        public String title;
        public String desc;
        public String content;

        @Override
        public String getState() {
            return BizState.BIZ_STATE;
        }
    }

}