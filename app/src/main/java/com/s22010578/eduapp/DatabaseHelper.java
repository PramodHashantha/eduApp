package com.s22010578.eduapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "edubridge.db";
    private static final int DATABASE_VERSION = 2;

    // Users table
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_USER_TYPE = "user_type";

    // Classes table
    private static final String TABLE_CLASSES = "classes";
    private static final String COLUMN_CLASS_ID = "id";
    private static final String COLUMN_CLASS_NAME = "name";
    private static final String COLUMN_CLASS_LOCATION = "location";
    private static final String COLUMN_TEACHER_EMAIL = "teacher_email";

    // Messages table
    private static final String TABLE_MESSAGES = "messages";
    private static final String COLUMN_MESSAGE_ID = "id";
    private static final String COLUMN_MESSAGE_TEXT = "text";
    private static final String COLUMN_MESSAGE_CLASS_ID = "class_id";
    private static final String COLUMN_MESSAGE_TIMESTAMP = "timestamp";

    // Class Students table
    private static final String TABLE_CLASS_STUDENTS = "class_students";
    private static final String COLUMN_CLASS_STUDENT_ID = "id";
    private static final String COLUMN_CLASS_STUDENT_CLASS_ID = "class_id";
    private static final String COLUMN_CLASS_STUDENT_EMAIL = "student_email";

    // Quizzes table
    private static final String TABLE_QUIZZES = "quizzes";
    private static final String COLUMN_QUIZ_ID = "quiz_id";
    private static final String COLUMN_QUIZ_TITLE = "quiz_title";
    private static final String COLUMN_QUIZ_CLASS_ID = "quiz_class_id";

    // Questions table
    private static final String TABLE_QUESTIONS = "questions";
    private static final String COLUMN_QUESTION_ID = "question_id";
    private static final String COLUMN_QUESTION_QUIZ_ID = "question_quiz_id";
    private static final String COLUMN_QUESTION_TEXT = "question_text";
    private static final String COLUMN_OPTION1 = "option1";
    private static final String COLUMN_OPTION2 = "option2";
    private static final String COLUMN_OPTION3 = "option3";
    private static final String COLUMN_OPTION4 = "option4";
    private static final String COLUMN_CORRECT_ANSWER = "correct_answer";

    // Quiz Results table
    private static final String TABLE_QUIZ_RESULTS = "quiz_results";
    private static final String COLUMN_QUIZ_RESULT_ID = "quiz_result_id";
    private static final String COLUMN_QUIZ_RESULT_STUDENT_EMAIL = "quiz_result_student_email";
    private static final String COLUMN_QUIZ_RESULT_QUIZ_ID = "quiz_result_quiz_id";
    private static final String COLUMN_QUIZ_RESULT_SCORE = "quiz_result_score";

    private static final String TABLE_ATTEMPTS = "attempts";
    private static final String COLUMN_ATTEMPT_ID = "id";
    private static final String COLUMN_ATTEMPT_STUDENT_ID = "student_id";
    private static final String COLUMN_ATTEMPT_QUIZ_ID = "quiz_id";
    private static final String COLUMN_ATTEMPT_SCORE = "score";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableUsers = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_USER_TYPE + " TEXT)";
        db.execSQL(createTableUsers);

        String createTableClasses = "CREATE TABLE " + TABLE_CLASSES + "("
                + COLUMN_CLASS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_CLASS_NAME + " TEXT,"
                + COLUMN_CLASS_LOCATION + " TEXT,"
                + COLUMN_TEACHER_EMAIL + " TEXT,"
                + "FOREIGN KEY(" + COLUMN_TEACHER_EMAIL + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_EMAIL + "))";
        db.execSQL(createTableClasses);

        String createTableMessages = "CREATE TABLE " + TABLE_MESSAGES + "("
                + COLUMN_MESSAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_MESSAGE_TEXT + " TEXT,"
                + COLUMN_MESSAGE_CLASS_ID + " INTEGER,"
                + COLUMN_MESSAGE_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
                + "FOREIGN KEY(" + COLUMN_MESSAGE_CLASS_ID + ") REFERENCES " + TABLE_CLASSES + "(" + COLUMN_CLASS_ID + "))";
        db.execSQL(createTableMessages);

        String createTableClassStudents = "CREATE TABLE " + TABLE_CLASS_STUDENTS + "("
                + COLUMN_CLASS_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_CLASS_STUDENT_CLASS_ID + " INTEGER,"
                + COLUMN_CLASS_STUDENT_EMAIL + " TEXT,"
                + "FOREIGN KEY(" + COLUMN_CLASS_STUDENT_CLASS_ID + ") REFERENCES " + TABLE_CLASSES + "(" + COLUMN_CLASS_ID + "),"
                + "FOREIGN KEY(" + COLUMN_CLASS_STUDENT_EMAIL + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_EMAIL + "))";
        db.execSQL(createTableClassStudents);

        // Quizzes table
        String createTableQuizzes = "CREATE TABLE " + TABLE_QUIZZES + "("
                + COLUMN_QUIZ_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_QUIZ_TITLE + " TEXT,"
                + COLUMN_QUIZ_CLASS_ID + " INTEGER,"
                + "FOREIGN KEY(" + COLUMN_QUIZ_CLASS_ID + ") REFERENCES " + TABLE_CLASSES + "(" + COLUMN_CLASS_ID + "))";
        db.execSQL(createTableQuizzes);


        // Questions table
        String createTableQuestions = "CREATE TABLE " + TABLE_QUESTIONS + "("
                + COLUMN_QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_QUESTION_QUIZ_ID + " INTEGER,"
                + COLUMN_QUESTION_TEXT + " TEXT,"
                + COLUMN_OPTION1 + " TEXT,"
                + COLUMN_OPTION2 + " TEXT,"
                + COLUMN_OPTION3 + " TEXT,"
                + COLUMN_OPTION4 + " TEXT,"
                + COLUMN_CORRECT_ANSWER + " TEXT,"
                + "FOREIGN KEY(" + COLUMN_QUESTION_QUIZ_ID + ") REFERENCES " + TABLE_QUIZZES + "(" + COLUMN_QUIZ_ID + "))";
        db.execSQL(createTableQuestions);


        // Quiz Results table
        String createTableQuizResults = "CREATE TABLE " + TABLE_QUIZ_RESULTS + "("
                + COLUMN_QUIZ_RESULT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_QUIZ_RESULT_STUDENT_EMAIL + " TEXT,"
                + COLUMN_QUIZ_RESULT_QUIZ_ID + " INTEGER,"
                + COLUMN_QUIZ_RESULT_SCORE + " INTEGER,"
                + "FOREIGN KEY(" + COLUMN_QUIZ_RESULT_QUIZ_ID + ") REFERENCES " + TABLE_QUIZZES + "(" + COLUMN_QUIZ_ID + "),"
                + "FOREIGN KEY(" + COLUMN_QUIZ_RESULT_STUDENT_EMAIL + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_EMAIL + "))";
        db.execSQL(createTableQuizResults);

        String CREATE_ATTEMPTS_TABLE = "CREATE TABLE " + TABLE_ATTEMPTS + "("
                + COLUMN_ATTEMPT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_ATTEMPT_STUDENT_ID + " INTEGER,"
                + COLUMN_ATTEMPT_QUIZ_ID + " INTEGER,"
                + COLUMN_ATTEMPT_SCORE + " INTEGER" + ")";
        db.execSQL(CREATE_ATTEMPTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS_STUDENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZZES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZ_RESULTS);
        onCreate(db);
    }

    // User-related methods

    public boolean addUser(String username, String email, String password, String userType) {
        if (isEmailExists(email)) {
            return false;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_USER_TYPE, userType);
        db.insert(TABLE_USERS, null, values);
        db.close();
        return true;
    }

    private boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public String validateUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_USER_TYPE + " FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email, password});

        if (cursor != null && cursor.moveToFirst()) {
            String userType = cursor.getString(0);
            cursor.close();
            return userType;
        }

        if (cursor != null) {
            cursor.close();
        }

        return null;
    }

    public ArrayList<User> getLoggedInUserDetails(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<User> userDetails = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});

        if (cursor.moveToFirst()) {
            int usernameIndex = cursor.getColumnIndexOrThrow(COLUMN_USERNAME);
            int emailIndex = cursor.getColumnIndexOrThrow(COLUMN_EMAIL);
            int userTypeIndex = cursor.getColumnIndexOrThrow(COLUMN_USER_TYPE);

            do {
                String userName = cursor.getString(usernameIndex);
                String userEmail = cursor.getString(emailIndex);
                String userType = cursor.getString(userTypeIndex);

                User user = new User();
                user.setUserName(userName);
                user.setUserEmail(userEmail);
                user.setUserType(userType);

                userDetails.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return userDetails;
    }

    // Class-related methods

    public void addClass(String name, String location, String teacherEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLASS_NAME, name);
        values.put(COLUMN_CLASS_LOCATION, location);
        values.put(COLUMN_TEACHER_EMAIL, teacherEmail);
        db.insert(TABLE_CLASSES, null, values);
        db.close();
    }

    public List<Class> getAllClasses(String teacherEmail) {
        List<Class> classList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_CLASSES + " WHERE " + COLUMN_TEACHER_EMAIL + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{teacherEmail});

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CLASS_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CLASS_NAME));
                String location = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CLASS_LOCATION));

                Class classItem = new Class();
                classItem.setId(id);
                classItem.setName(name);
                classItem.setLocation(location);
                classItem.setTeacherEmail(teacherEmail);

                classList.add(classItem);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return classList;
    }

    public void updateClass(int id, String name, String location, String teacherEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLASS_NAME, name);
        values.put(COLUMN_CLASS_LOCATION, location);
        values.put(COLUMN_TEACHER_EMAIL, teacherEmail);
        db.update(TABLE_CLASSES, values, COLUMN_CLASS_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteClass(int id, String teacherEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CLASSES, COLUMN_CLASS_ID + " = ? AND " + COLUMN_TEACHER_EMAIL + " = ?", new String[]{String.valueOf(id), teacherEmail});
        db.close();
    }


    // Message-related methods

    public void addMessage(String text, int classId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MESSAGE_TEXT, text);
        values.put(COLUMN_MESSAGE_CLASS_ID, classId);
        db.insert(TABLE_MESSAGES, null, values);
        db.close();
    }

    public List<String> getAllMessagesForClass(int classId) {
        List<String> messageList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_MESSAGES + " WHERE " + COLUMN_MESSAGE_CLASS_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(classId)});

        if (cursor.moveToFirst()) {
            do {
                int columnIndex = cursor.getColumnIndexOrThrow(COLUMN_MESSAGE_TEXT);
                String messageText = cursor.getString(columnIndex);
                messageList.add(messageText);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return messageList;
    }

    public void updateMessage(int messageId, String newText) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MESSAGE_TEXT, newText);
        db.update(TABLE_MESSAGES, values, COLUMN_MESSAGE_ID + " = ?", new String[]{String.valueOf(messageId)});
        db.close();
    }

    public void deleteMessage(int messageId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MESSAGES, COLUMN_MESSAGE_ID + " = ?", new String[]{String.valueOf(messageId)});
        db.close();
    }
    public List<String> getStudentsInClass(int classId) {
        List<String> students = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_CLASS_STUDENT_EMAIL + " FROM " + TABLE_CLASS_STUDENTS + " WHERE " + COLUMN_CLASS_STUDENT_CLASS_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(classId)});
        if (cursor.moveToFirst()) {
            do {
                students.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CLASS_STUDENT_EMAIL)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return students;
    }

    public boolean addStudentToClass(int classId, String studentEmail) {
        if (!isEmailExists(studentEmail)) {
            return false;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLASS_STUDENT_CLASS_ID, classId);
        values.put(COLUMN_CLASS_STUDENT_EMAIL, studentEmail);
        long result = db.insert(TABLE_CLASS_STUDENTS, null, values);
        db.close();
        return result != -1;
    }

    public boolean removeStudentFromClass(int classId, String studentEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_CLASS_STUDENTS, COLUMN_CLASS_STUDENT_CLASS_ID + " = ? AND " + COLUMN_CLASS_STUDENT_EMAIL + " = ?", new String[]{String.valueOf(classId), studentEmail});
        db.close();
        return result > 0;
    }
    public List<Class> getClassesForStudent(String studentEmail) {
        List<Class> classList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // First, get the class IDs the student is enrolled in
        String queryClassIds = "SELECT " + COLUMN_CLASS_STUDENT_CLASS_ID + " FROM " + TABLE_CLASS_STUDENTS +
                " WHERE " + COLUMN_CLASS_STUDENT_EMAIL + " = ?";
        Cursor cursorClassIds = db.rawQuery(queryClassIds, new String[]{studentEmail});

        List<Integer> classIds = new ArrayList<>();
        if (cursorClassIds.moveToFirst()) {
            do {
                int classId = cursorClassIds.getInt(cursorClassIds.getColumnIndexOrThrow(COLUMN_CLASS_STUDENT_CLASS_ID));
                classIds.add(classId);
            } while (cursorClassIds.moveToNext());
        }
        cursorClassIds.close();

        // Then, get the class details for each class ID
        for (int classId : classIds) {
            String queryClasses = "SELECT * FROM " + TABLE_CLASSES + " WHERE " + COLUMN_CLASS_ID + " = ?";
            Cursor cursorClasses = db.rawQuery(queryClasses, new String[]{String.valueOf(classId)});

            if (cursorClasses.moveToFirst()) {
                int id = cursorClasses.getInt(cursorClasses.getColumnIndexOrThrow(COLUMN_CLASS_ID));
                String name = cursorClasses.getString(cursorClasses.getColumnIndexOrThrow(COLUMN_CLASS_NAME));
                String location = cursorClasses.getString(cursorClasses.getColumnIndexOrThrow(COLUMN_CLASS_LOCATION));
                String teacherEmail = cursorClasses.getString(cursorClasses.getColumnIndexOrThrow(COLUMN_TEACHER_EMAIL));

                Class classItem = new Class();
                classItem.setId(id);
                classItem.setName(name);
                classItem.setLocation(location);
                classItem.setTeacherEmail(teacherEmail);

                classList.add(classItem);
            }
            cursorClasses.close();
        }

        db.close();
        return classList;
    }
    // Quiz-related methods
    public long addQuiz(String title, int classId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUIZ_TITLE, title);
        values.put(COLUMN_QUIZ_CLASS_ID, classId);
        long quizId = db.insert(TABLE_QUIZZES, null, values);
        db.close();
        return quizId;
    }

    public List<Quiz> getAllQuizzesForClass(int classId) {
        List<Quiz> quizList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_QUIZZES + " WHERE " + COLUMN_QUIZ_CLASS_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(classId)});
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUIZ_ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QUIZ_TITLE));
                Quiz quiz = new Quiz(id, title, classId);
                quizList.add(quiz);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return quizList;
    }

    // Question-related methods
    public long addQuestion(int quizId, String questionText, String option1, String option2, String option3, String option4, String correctAnswer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION_QUIZ_ID, quizId);
        values.put(COLUMN_QUESTION_TEXT, questionText);
        values.put(COLUMN_OPTION1, option1);
        values.put(COLUMN_OPTION2, option2);
        values.put(COLUMN_OPTION3, option3);
        values.put(COLUMN_OPTION4, option4);
        values.put(COLUMN_CORRECT_ANSWER, correctAnswer);
        long questionId = db.insert(TABLE_QUESTIONS, null, values);
        db.close();
        return questionId;
    }

    public List<Question> getAllQuestionsForQuiz(int quizId) {
        List<Question> questionList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_QUESTIONS + " WHERE " + COLUMN_QUESTION_QUIZ_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(quizId)});
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUESTION_ID));
                String questionText = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QUESTION_TEXT));
                String option1 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OPTION1));
                String option2 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OPTION2));
                String option3 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OPTION3));
                String option4 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OPTION4));
                String correctAnswer = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CORRECT_ANSWER));
                Question question = new Question(id, quizId, questionText, option1, option2, option3, option4, correctAnswer);
                questionList.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return questionList;
    }
    public long addAttempt(int studentId, int quizId, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ATTEMPT_STUDENT_ID, studentId);
        values.put(COLUMN_ATTEMPT_QUIZ_ID, quizId);
        values.put(COLUMN_ATTEMPT_SCORE, score);

        long id = db.insert(TABLE_ATTEMPTS, null, values);
        db.close();
        return id;
    }

    public List<Attempt> getAttemptsForStudent(int studentId) {
        List<Attempt> attemptList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_ATTEMPTS + " WHERE " + COLUMN_ATTEMPT_STUDENT_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(studentId)});
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ATTEMPT_ID));
                int quizId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ATTEMPT_QUIZ_ID));
                int score = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ATTEMPT_SCORE));
                Attempt attempt = new Attempt(id, studentId, quizId, score);
                attemptList.add(attempt);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return attemptList;
    }
}
