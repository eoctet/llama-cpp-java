package chat.octet.model;

import chat.octet.exceptions.ServerException;
import chat.octet.utils.CommonUtils;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentMap;

@Slf4j
public final class UserContextManager {
    public static final int USER_CONTEXT_LIMIT = 100;
    public static final String DEFAULT_USER_ID = "octet_default_user";

    private static final ConcurrentMap<String, UserContext> USER_CONTEXT_CACHE = Maps.newConcurrentMap();
    private static volatile UserContextManager manager;

    private UserContextManager() {
    }

    public static UserContextManager getInstance() {
        if (manager == null) {
            synchronized (UserContextManager.class) {
                if (manager == null) {
                    manager = new UserContextManager();
                }
            }
        }
        return manager;
    }

    public UserContext getDefaultUserContext(LlamaModel model) {
        return createUserContext(DEFAULT_USER_ID, model.getContextSize(), model.getVocabSize());
    }

    public UserContext createUserContext(String id, int contextSize, int vocabSize) {
        boolean exists = USER_CONTEXT_CACHE.containsKey(id);
        if (!exists) {
            if (USER_CONTEXT_CACHE.size() > USER_CONTEXT_LIMIT) {
                throw new ServerException("Cache context size is out of limit: " + USER_CONTEXT_LIMIT);
            }
            UserContext userContext = new UserContext(id, contextSize, vocabSize);
            USER_CONTEXT_CACHE.put(id, userContext);
            log.info(CommonUtils.format("Create new user context, User id: {0}, user context cache size: {1}.", userContext.getId(), USER_CONTEXT_CACHE.size()));
        }
        return USER_CONTEXT_CACHE.get(id);
    }

    public void removeUserContext(String id) {
        boolean exists = USER_CONTEXT_CACHE.containsKey(id);
        if (exists) {
            USER_CONTEXT_CACHE.get(id).destory();
            USER_CONTEXT_CACHE.remove(id);
            log.info(CommonUtils.format("Removed user context, User id: {0}.", id));
        }
    }

    public void removeAllUsersContext() {
        int size = USER_CONTEXT_CACHE.size();
        USER_CONTEXT_CACHE.keySet().forEach(this::removeUserContext);
        log.info(CommonUtils.format("Removed all users context, size: {0}.", size));
    }

}