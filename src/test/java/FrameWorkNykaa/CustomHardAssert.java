package FrameWorkNykaa;

import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;

public class CustomHardAssert extends Assertion {

    @Override
    public void onAssertFailure(IAssert<?> assertCommand) {
        String CaseID = assertCommand.getMessage();


        // TODO Auto-generated method stub
        super.onAssertFailure(assertCommand);
    }


}
