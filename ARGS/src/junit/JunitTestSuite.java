package junit;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestAdaptor.class,
        TestCharacterFrame.class,
        TestInventoryFrame.class,
        TestLoadCampaign.class,
        TestLoadChar.class,
        TestLoadItem.class,
        TestMap.class,
        TestMapLoad.class,
        TestPanelListener.class
})
public class JunitTestSuite {
}
