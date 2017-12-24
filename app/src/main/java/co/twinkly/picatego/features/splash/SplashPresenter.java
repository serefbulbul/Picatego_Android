package co.twinkly.picatego.features.splash;

import android.os.Handler;

import co.twinkly.picatego.features.base.BasePresenter;
import co.twinkly.picatego.utils.constants.SharedPreferencesConstants;
import co.twinkly.picatego.utils.managers.SharedPreferencesManager;
import co.twinkly.picatego.utils.services.database.DatabaseService;
import co.twinkly.picatego.utils.services.database.models.Category;

public class SplashPresenter extends BasePresenter implements SplashContract.Presenter {

    private final SplashContract.View view;
    private final SplashContract.Interactor interactor;
    private final SharedPreferencesManager sharedPreferencesManager;
    private final DatabaseService databaseService;

    public SplashPresenter(SplashContract.View view, SplashContract.Interactor interactor, SharedPreferencesManager sharedPreferencesManager, DatabaseService databaseService) {
        this.view = view;
        this.interactor = interactor;
        this.sharedPreferencesManager = sharedPreferencesManager;
        this.databaseService = databaseService;
    }

    @Override
    public void subscribe() {
        super.subscribe();

        boolean isFirstRun = sharedPreferencesManager.getBooleanValue(SharedPreferencesConstants.IS_FIRST_RUN, true);

        if (isFirstRun) {
            sharedPreferencesManager.put(SharedPreferencesConstants.IS_FIRST_RUN, false);
            createCategories();
        }

        new Handler().postDelayed(() -> {
            boolean isFirstTimeUser = sharedPreferencesManager.getBooleanValue(SharedPreferencesConstants.IS_FIRST_TIME_USER, true);

            if (isFirstTimeUser) {
                view.startCategorySelectionActivity();
            } else {
                view.startWelcomeActivity();
            }
        }, 700);
    }

    @Override
    public void createCategories() {
        databaseService.createCategory(new Category("Motocycle", 0));
        databaseService.createCategory(new Category("Nature", 0));
        databaseService.createCategory(new Category("Camping", 0));
        databaseService.createCategory(new Category("Snowboard", 0));
        databaseService.createCategory(new Category("Wakeboard", 0));
        databaseService.createCategory(new Category("Interrail", 0));
    }
}
