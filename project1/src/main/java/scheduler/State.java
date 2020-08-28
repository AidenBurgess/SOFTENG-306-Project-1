package main.java.scheduler;

/**
 * The State class holds information that defines an allocation of tasks on processors.
 */
public class State {
    protected static final int UNSCHEDULED = -1;

    // the total number of tasks and processors
    protected final int _numTasks, _numProcessors;

    // total computational time used for the FFunction
    protected int _computationalTime;

    protected int[] _assignedProcessorId; // assignedProcessorId[taskId] -> processorId
    protected int[] _taskEndTime; // taskEndTime[taskId] -> end time of task
    protected int[] _processorEndTime; // processorEndTime[processorId] -> end time of processor

    // end time of the last processor
    protected int _endTime;

    // number of tasks still unassigned
    protected int _unassignedTasks;

    // current free processor
    protected int _freeProcessor;

    // the first task of the previous processor
    protected int _prevProcessorFirstTask;

    /**
     * Initialises all of the fields to be used later.
     * @param numTasks
     * @param numProcessors
     */
    private State(int numTasks, int numProcessors) {
        _numTasks = numTasks;
        _numProcessors = numProcessors;

        // initially n tasks are unassigned
        _unassignedTasks = numTasks;
        _endTime = 0;
        _freeProcessor = 0;
        _prevProcessorFirstTask = UNSCHEDULED;

        _processorEndTime = new int[numProcessors];
        _assignedProcessorId = new int[numTasks];
        _taskEndTime = new int[numTasks];

        for (int i = 0; i < numTasks; i++) {
            _taskEndTime[i] = UNSCHEDULED;
            _assignedProcessorId[i] = UNSCHEDULED;
        }

        for (int i = 0; i < numProcessors; i++) {
            _processorEndTime[i] = 0;
        }
    }

    /**
     * Initialises the total task weight when the data structures are added.
     * @param numTasks
     * @param numProcessors
     * @param dataStructures
     */
    public State(int numTasks, int numProcessors, DataStructures dataStructures) {
        this(numTasks, numProcessors);
        int taskWeight = 0;
        for (int i = 0; i < numTasks; i++) {
            taskWeight += dataStructures.getTaskWeights().get(i);
        }

        _computationalTime = taskWeight;
    }

    /**
     * Returns a deep copy of the current State
     *
     * @return a new State instance with the same field values.
     */
    public State copy() {
        State next = new State(_numTasks, _numProcessors);

        System.arraycopy(_taskEndTime, 0, next._taskEndTime, 0, _numTasks);
        System.arraycopy(_assignedProcessorId, 0, next._assignedProcessorId, 0, _numTasks);
        System.arraycopy(_processorEndTime, 0, next._processorEndTime, 0, _numProcessors);

        next._endTime = _endTime;
        next._unassignedTasks = _unassignedTasks;
        next._computationalTime = _computationalTime;
        next._freeProcessor = _freeProcessor;
        next._prevProcessorFirstTask = _prevProcessorFirstTask;
        return next;
    }
}
