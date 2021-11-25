import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//@author A0135280M
public class Storage {
    
    private Map<Integer, Task> tasks;
    private StorageIO storageIO;
    private StorageSearch storageSearch;
    private int lastIdNumber;
    
    public Storage(StorageIO _storageIO) {
        storageIO = _storageIO;
    }
    /**
     * Initialize StorageSerach and StorageIO, file paths and the data structure
     * for storing tasks.
     * 
     * @return feedback string
     */
    public String prepareStorage() throws IOException {
        storageSearch = new StorageSearch();
        String storageFilePath = storageIO.initializeConfigFile();
        String setStorageFileFeedback = storageIO.setFilePath(storageFilePath);
        initializeTaskList();
        return setStorageFileFeedback;
    }

    /**
     * Create a HashMap with Tasks and their id as key from the storage file
     */
    private void initializeTaskList() {
        tasks = new HashMap<Integer, Task>();
        storageIO.getDataFromFile(tasks);
        lastIdNumber = storageIO.getLastIdNumber();
    }

    /**
     * Change the path for task storage and updates the tasks
     * 
     * @param path  the new path given by the user
     * @return      feedback string
     */
    public String setFilePath(String path) throws IOException {
        String feedback = storageIO.setFilePath(path);
        initializeTaskList();
        return feedback;
    }
    
    public String getFilePath() {
        return storageIO.getFilePath();
    }

    private int getNextIdNo() {
        lastIdNumber++;
        return lastIdNumber;
    }
    
    public String add(Task newTask) {
        int taskId = newTask.getId();
        if (taskId == Constants.NO_ID_GIVEN) {
            taskId = getNextIdNo();
            newTask.setId(taskId);
        }
        tasks.put(taskId, newTask);
        writeToFile();
        return newTask.getUserFormat();
    }
    
    public String delete(int id) {
        assert id > 0;
        Task removedTask = tasks.get(id);
        if (removedTask != null) {
            tasks.remove(id);
            writeToFile();
            return String.format(Constants.MESSAGE_DELETED, removedTask.getId());
        }
        return Constants.MESSAGE_INCORRECT_ID;
    }
    
    public String deleteAll() {
        tasks = new HashMap<Integer, Task>();
        writeToFile();
        return String.format(Constants.MESSAGE_ALL_DELETED);
    }
    
    public String done(int id, boolean setDone) {
        if (tasks.get(id) != null) {
            Task doneTask = tasks.get(id);
            doneTask.setDone(setDone);
            tasks.put(id, doneTask);
            writeToFile();
            return doneTask.getUserFormat();
        }
        return Constants.MESSAGE_INCORRECT_ID;
    }
    
    private String updateTask(int id, Task updatedTask) {
        if (tasks.get(id) != null) {
            tasks.put(id, updatedTask);
            writeToFile();
            return String.format(Constants.MESSAGE_UPDATED, id);
        }
        return Constants.MESSAGE_INCORRECT_ID;
    }
    
    public String updateDescription(int id, String newDesc) {
        Task taskToUpdate = tasks.get(id);
        if (taskToUpdate != null) {
            taskToUpdate.setDescription(newDesc);
            return updateTask(id, taskToUpdate);
        }
        return Constants.MESSAGE_INCORRECT_ID;
    }
    
    public String updateStartDate(int id, LocalDateTime startDate) {
        Task taskToUpdate = tasks.get(id);
        if (taskToUpdate != null) {
            taskToUpdate.setStartDateTime(startDate);
            return updateTask(id, taskToUpdate);
        }
        return Constants.MESSAGE_INCORRECT_ID;
    }
    
    public String updateEndDate(int id, LocalDateTime endDate) {
        Task taskToUpdate = tasks.get(id);
        if (taskToUpdate != null) {
            taskToUpdate.setEndDateTime(endDate);
            return updateTask(id, taskToUpdate);
        }
        return Constants.MESSAGE_INCORRECT_ID;
    }
    
    public Task getTask(int id) {
        if (tasks.get(id) != null) {
            return tasks.get(id);
        }
        return null;
    }
    
    public Task getLastAddedTask() {
        return getTask(lastIdNumber);
    }
    
    /**
     * Returns array with all tasks
     */
    public Task[] getAllTasks() {
        Task[] taskArray = new Task[tasks.size()];
        int i = 0;
        for (Task task : tasks.values()) {
            taskArray[i] = task;
            i++;
        }
        return taskArray;
    }
    
    /**
     * Returns a string with all task information formatted as a table with the 
     * done tasks at the top, then floating tasks and last the unfinished 
     * deadline and event tasks, ordered by date. 
     * 
     * @return  all tasks as a user friendly formatted string 
     */
    public String getAllTasksAsString() {
        String allTasks = getDoneTasksAsString();
        
        ArrayList<Task> unfinishedTasks = getUnfinishedTasks();
        for (Task newTask : unfinishedTasks) {
            allTasks += "\n" + newTask.getUserFormat();
        }
        
        if (allTasks.equals("")) {
            return Constants.MESSAGE_NO_TASKS;
        }
        String feedback = "\n" + Constants.MESSAGE_DISPLAY_ALL + 
                Constants.DISPLAY_TABLE_HEADERS + allTasks;
        return feedback;
    }
    
    /**
     * Returns all unfinished tasks ordered by date (closest deadline at the 
     * bottom) and with floating tasks at the top
     * 
     * @return  ArrayList with unfinished tasks
     */
    private ArrayList<Task> getUnfinishedTasks() {
        Task[] taskArray = getAllTasks();
        ArrayList<Task> unfinishedTasks = new ArrayList<Task>();
        for (Task task : taskArray) {
            if (!task.isDone()) {
                unfinishedTasks.add(task);
            }
        }
        Collections.sort(unfinishedTasks);
        return unfinishedTasks;
    }
    
    /**
     * Returns a string with all tasks in a given time span, formatted as a 
     * table with the done tasks at the top, then floating tasks and last the 
     * unfinished deadline and event tasks, ordered by date. 
     * 
     * @param displayObj    Task with the given time span as startDateTime and 
     *                      endDateTime
     * @return tasks in the time span as a user friendly formatted string 
     */
    public String getTasksInTimeSpan(Task displayObj) {
        String displayTasks = "\n";
        displayTasks += getDisplayTitle(displayObj);
        
        ArrayList<Task> unfinishedTasks = getUnfinishedTasks();        
        String searchResult = storageSearch.search(unfinishedTasks, displayObj);
        String floatingTasks = getFloatingTasksAsString();
        
        if (searchResult == "" && floatingTasks == "") {
            displayTasks += Constants.MESSAGE_NO_TASKS;
        } else {
            displayTasks += Constants.DISPLAY_TABLE_HEADERS;
            displayTasks += floatingTasks;
            displayTasks += searchResult;
        }
        
        return displayTasks;
    }
    
    private String getDisplayTitle(Task displayObj) {
        String startTime;
        String endTime;
        if (displayObj.isDeadlineTask()) {
            startTime = Constants.DISPLAY_TIME_NOW;
        } else {
            startTime = displayObj.getStartDateForDisplay();
        }
        endTime = displayObj.getEndDateForDisplay();
        return String.format(Constants.MESSAGE_TIME_PERIOD, startTime, endTime);
    }
    
    public String getDoneTasksAsString() {
        String doneTasksString = "";
        for (Task task : tasks.values()) {
            if (task.isDone()) {
                doneTasksString += "\n" + task.getUserFormat();
            }
        }
        return doneTasksString;
    }
    
    private String getFloatingTasksAsString() {
        String floatingTasksString = "";
        for (Task task : tasks.values()) {
            if (task.isFloatingTask() && !task.isDone()) {
                floatingTasksString += "\n" + task.getUserFormat();
            }
        }
        return floatingTasksString;
    }
    
    /**
     * Make a search given the search criterias in the searchObj
     * 
     * @param searchObj
     * @return the search result as a string
     */
    public String search(Task searchObj) {
        String feedback;
        ArrayList<Task> taskList = new ArrayList<Task>(tasks.values());
        String result = storageSearch.search(taskList, searchObj);
        if (result.equals("")) {
            feedback = Constants.MESSAGE_SEARCH_UNSUCCESSFUL;
        } else {
            feedback = Constants.DISPLAY_TABLE_HEADERS+"\n"+result;
        }
        return feedback;
    }
    
    public void writeToFile() {
        storageIO.writeToFile(tasks);
    }

}
