package nuricanozturk.dev.action.actions;

import nuricanozturk.dev.action.ActionGenerator;
import nuricanozturk.dev.action.ActionType;
import project.gameengine.base.Action;

public final class ActionFactory {
    private final static Action m_buyFuelAction = new BuyFuel();
    private final static Action m_buyItemAction = new BuyItem();
    private final static Action m_buySpaceShipAction = new BuySpaceship();
    private final static Action m_soldItemAction = new SellItem();
    private final static Action m_planTravellingAction = new PlanTravelling();
    private final static Action m_selectPlanetAction = new SelectPlanet();
    private final static Action m_sellSpaceshipAction = new SellSpaceship();
    private final static Action m_changeSpaceshipAction = new ChangeSpaceship();
    private static ActionGenerator m_actionGenerator;

    private ActionFactory() {

    }

    public static Action getAction(ActionType actionType) {
        return switch (actionType) {
            case BUY_FUEL -> m_buyFuelAction;
            case BUY_ITEM -> m_buyItemAction;
            case SOLD_ITEM -> m_soldItemAction;
            default -> m_planTravellingAction;
        };
    }

}
