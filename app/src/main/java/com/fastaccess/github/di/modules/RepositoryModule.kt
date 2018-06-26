package com.fastaccess.github.di.modules

import com.fastaccess.data.persistence.db.FastHubDatabase
import com.fastaccess.data.persistence.db.FastHubLoginDatabase
import com.fastaccess.data.repository.*
import com.fastaccess.domain.repository.services.LoginService
import com.fastaccess.domain.repository.services.UserService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Kosh on 11.05.18.
 */
@Module
class RepositoryModule {
    @Singleton @Provides fun provideLoginRepository(fastHubLoginDatabase: FastHubLoginDatabase, loginService: LoginService): LoginRepositoryProvider {
        return LoginRepositoryProvider(fastHubLoginDatabase.provideLoginDao(), loginService)
    }

    @Singleton @Provides fun provideUserRepository(userService: UserService,
                                                   gson: Gson): UserRepositoryProvider {
        return UserRepositoryProvider(userService, gson)
    }

    @Singleton @Provides fun provideMainIssuesPullsRepository(fastHubDatabase: FastHubDatabase): MainIssuesPullsRepositoryProvider {
        return MainIssuesPullsRepositoryProvider(fastHubDatabase.getMainIssuesPullsDao())
    }

    @Singleton @Provides fun provideNotificationRepositoryProvider(fastHubDatabase: FastHubDatabase): NotificationRepositoryProvider {
        return NotificationRepositoryProvider(fastHubDatabase.getNotifications())
    }

    @Singleton @Provides fun provideFeedsRepositoryProvider(userService: UserService,
                                                            loginRepositoryProvider: LoginRepositoryProvider): FeedsRepositoryProvider {
        return FeedsRepositoryProvider(userService, loginRepositoryProvider)
    }
}