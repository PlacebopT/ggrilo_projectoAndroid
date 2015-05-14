package ggrilo.millionaireapp;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Contract to be used in {@link QuestionsAnswersProvider}.
 * @pt Contrato que define constantes a serem utilizadas pelo ContentProvider.
 *
 * @author Challenge.IT
 */
public class QuestionsAnswersContract
{
    // table name
    public static final String TABLE = "MILLIONAIRE";

    // columns names
    public static final String _ID = BaseColumns._ID;
    public static final String VALUE = "value";

    // content URI for subset of provided data from temperature provider.
    public static Uri CONTENT_PROVIDER = Uri.withAppendedPath(QuestionsAnswersProvider.CONTENT_URI, TABLE);
}
