package run.aiwan.score;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private MutableLiveData<Integer> ATeamScore;
    private MutableLiveData<Integer> BTeamScore;
    private int aBack,bBack;

    public MutableLiveData<Integer> getATeamScore() {
        if (ATeamScore == null) {
            ATeamScore = new MutableLiveData<>();
            ATeamScore.setValue(0);
        }
        return ATeamScore;
    }

    public MutableLiveData<Integer> getBTeamScore() {
        if (BTeamScore == null) {
            BTeamScore = new MutableLiveData<>();
            BTeamScore.setValue(0);
        }
        return BTeamScore;
    }

    public void ATeamAdd(int n) {
        aBack = ATeamScore.getValue();
        bBack = BTeamScore.getValue();
        ATeamScore.setValue(ATeamScore.getValue() + n);
    }

    public void BTeamAdd(int n) {
        aBack = ATeamScore.getValue();
        bBack = BTeamScore.getValue();
        BTeamScore.setValue(BTeamScore.getValue() + n);
    }

    public void reset() {
        aBack = ATeamScore.getValue();
        bBack = BTeamScore.getValue();
        ATeamScore.setValue(0);
        BTeamScore.setValue(0);
    }

    public void undo() {
        ATeamScore.setValue(aBack);
        BTeamScore.setValue(bBack);
    }
}
