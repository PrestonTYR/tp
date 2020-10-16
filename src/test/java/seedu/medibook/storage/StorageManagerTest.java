package seedu.medibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.medibook.testutil.TypicalPatients.getTypicalMediBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.medibook.commons.core.GuiSettings;
import seedu.medibook.model.MediBook;
import seedu.medibook.model.ReadOnlyMediBook;
import seedu.medibook.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonMediBookStorage mediBookStorage = new JsonMediBookStorage(getTempFilePath("mb"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonMedicalNoteListStorage medicalNoteListStorage =
                new JsonMedicalNoteListStorage(getTempFilePath("mnl"));
        storageManager = new StorageManager(mediBookStorage, userPrefsStorage, medicalNoteListStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void mediBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonMediBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonMediBookStorageTest} class.
         */
        MediBook original = getTypicalMediBook();
        storageManager.saveMediBook(original);
        ReadOnlyMediBook retrieved = storageManager.readMediBook().get();
        assertEquals(original, new MediBook(retrieved));
    }

    @Test
    public void getMediBookFilePath() {
        assertNotNull(storageManager.getMediBookFilePath());
    }

}
