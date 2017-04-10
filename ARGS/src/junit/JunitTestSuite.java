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
        TestPanelListener.class,
        TestBullyBuilder.class,
        TestNimbleBuilder.class,
        TestTankBuilder.class,
        TestFreezing.class,
        TestFrightening.class,
        TestPacifying.class,
        TestAttackRoll.class,
        TestCharacters.class,
        TestAggressive.class,
        TestFriendly.class,
        TestFrightenedStrategy.class,
        TestFrozenStrategy.class
        
})
public class JunitTestSuite {
}
