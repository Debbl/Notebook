package run.aiwan.livedatatest;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelWithLiveData extends ViewModel {
    private MutableLiveData<Integer> LikeDNumber;

    public MutableLiveData<Integer> getLikeDNumber() {
        if (LikeDNumber == null) {
            LikeDNumber = new MutableLiveData<>();
            LikeDNumber.setValue(0);
        }
        return LikeDNumber;
    }

    public void addNumber(int n) {
        LikeDNumber.setValue(LikeDNumber.getValue() + n);
    }
}
