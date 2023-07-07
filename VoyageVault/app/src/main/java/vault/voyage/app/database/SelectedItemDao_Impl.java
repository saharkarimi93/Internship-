package vault.voyage.app.database;

import android.database.Cursor;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vault.voyage.app.model.SelectedItem;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SelectedItemDao_Impl implements SelectedItemDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SelectedItem> __insertionAdapterOfSelectedItem;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  public SelectedItemDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSelectedItem = new EntityInsertionAdapter<SelectedItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `selectedItems` (`id`,`username`,`info`,`category`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SelectedItem value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getUsername() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUsername());
        }
        if (value.getInfo() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getInfo());
        }
        if (value.getCategory() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getCategory());
        }
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM selectedItems WHERE username=? AND info=? AND category= ? ";
        return _query;
      }
    };
  }

  @Override
  public void insert(final SelectedItem item) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSelectedItem.insert(item);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final String username, final String info, final String category) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDelete.acquire();
    int _argIndex = 1;
    if (username == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, username);
    }
    _argIndex = 2;
    if (info == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, info);
    }
    _argIndex = 3;
    if (category == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, category);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDelete.release(_stmt);
    }
  }

  @Override
  public List<SelectedItem> getAllSelectedItems(final String username) {
    final String _sql = "SELECT * FROM selectedItems where username=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (username == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, username);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
      final int _cursorIndexOfInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "info");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final List<SelectedItem> _result = new ArrayList<SelectedItem>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final SelectedItem _item;
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        final String _tmpUsername;
        if (_cursor.isNull(_cursorIndexOfUsername)) {
          _tmpUsername = null;
        } else {
          _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        }
        final String _tmpInfo;
        if (_cursor.isNull(_cursorIndexOfInfo)) {
          _tmpInfo = null;
        } else {
          _tmpInfo = _cursor.getString(_cursorIndexOfInfo);
        }
        final String _tmpCategory;
        if (_cursor.isNull(_cursorIndexOfCategory)) {
          _tmpCategory = null;
        } else {
          _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        }
        _item = new SelectedItem(_tmpId,_tmpUsername,_tmpInfo,_tmpCategory);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
