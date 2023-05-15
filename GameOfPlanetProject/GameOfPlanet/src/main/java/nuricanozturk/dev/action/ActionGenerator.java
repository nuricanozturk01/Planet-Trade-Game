package nuricanozturk.dev.action;

import nuricanozturk.dev.util.LinkedList;
import project.gameengine.base.Action;

import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;
import static nuricanozturk.dev.util.Util.LOGGER;

public final class ActionGenerator
{
    private final static Action m_buyFuelAction = new BuyFuel();
    private final static Action m_buyItemAction = new BuyItem();
    private final static Action m_buySpaceShipAction = new BuySpaceship();
    private final static Action m_soldItemAction = new SoldItem();
    private final static Action m_planTravellingAction = new PlanTravelling();
    private final static Action m_selectPlanetAction = new SelectPlanet();
    private static ActionGenerator m_actionGenerator;
    private final ActionType[] m_actionTypes;

    private ActionGenerator()
    {
        m_actionTypes = ActionType.values();
        LOGGER.log("\n");
    }

    public static ActionGenerator getActionGeneratorInstance()
    {
        return m_actionGenerator == null ? new ActionGenerator() : m_actionGenerator;
    }

    //------------------------------------------------------------------------------------------
    private ActionType getRandomActionType()
    {
        return m_actionTypes[getRandomInstance().nextInt(0, m_actionTypes.length)];
    }

    public Action getRandomAction()
    {
        return getAction(getRandomActionType());
    }

    public Action getBuySpacehipAction()
    {
        return m_buySpaceShipAction;
    }

    public Action getSelectPlanetAction()
    {
        return m_selectPlanetAction;
    }

    /*public List<Action> getActions()
    {

        return Arrays.asList(m_buyItemAction, m_soldItemAction, m_buyFuelAction, m_planTravellingAction);
    }*/

    public LinkedList<Action> getActionLinkedList()
    {
        var actionLinkedList = new LinkedList<Action>();

        actionLinkedList.insertFirst(m_planTravellingAction);
        actionLinkedList.insertFirst(m_buyFuelAction);
        actionLinkedList.insertFirst(m_soldItemAction);
        actionLinkedList.insertFirst(m_buyItemAction);

        return actionLinkedList;
    }

    public Action getAction(ActionType actionType)
    {
        return switch (actionType)
        {
            case BUY_FUEL -> m_buyFuelAction;
            case BUY_ITEM -> m_buyItemAction;
            case SOLD_ITEM -> m_soldItemAction;

            default -> m_planTravellingAction;
        };
    }
}