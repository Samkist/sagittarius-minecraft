package dev.samkist.lumae.sagittarius.storage;

import com.google.gson.reflect.TypeToken;
import dev.samkist.lumae.sagittarius.api.SagittariusApi;
import dev.samkist.lumae.sagittarius.compute.model.ModelLoader;
import dev.samkist.lumae.sagittarius.compute.remote.model.ModelLoaderRemoteInterfaceAsync;
import dev.samkist.lumae.sagittarius.data.models.global.ChatFormat;
import dev.samkist.lumae.sagittarius.data.models.global.JoinLeaveFormat;
import dev.samkist.lumae.sagittarius.data.models.global.MilkyPlayer;
import dev.samkist.lumae.sagittarius.data.models.global.ServerLocation;
import dev.samkist.lumae.sagittarius.exceptions.APIRuntimeException;
import dev.samkist.lumae.sagittarius.exceptions.PlayerNotFoundRuntimeException;
import dev.samkist.lumae.sagittarius.storage.redis.RedisProvider;
import dev.samkist.lumae.sagittarius.storage.redis.compute.ComputeServiceProvider;
import kong.unirest.HttpResponse;
import org.bukkit.entity.Player;
import org.redisson.api.RRemoteService;

import java.util.Optional;
import java.util.UUID;

public class DataProvider {

    private final RESTProvider restProvider;
    private final RedisProvider redisProvider;
    private final ComputeServiceProvider computeServiceProvider;
    private final ModelLoader modelLoader;


    public DataProvider() {
        SagittariusApi api = SagittariusApi.instance();
        restProvider = api.restProvider();
        redisProvider = api.redisProvider();
        computeServiceProvider = api.computeServiceProvider();
        modelLoader = new ModelLoader(redisProvider.client(), restProvider);
    }

    public MilkyPlayer getOrGenerateMilkyPlayer(Player player) {
        RRemoteService remoteService = redisProvider.client().getRemoteService();
        ModelLoaderRemoteInterfaceAsync asyncService = remoteService.get(ModelLoaderRemoteInterfaceAsync.class);
        try {
            return asyncService.loadModel(new TypeToken<MilkyPlayer>(){}, player.getUniqueId().toString()).get();
        } catch (Exception e) {
            return null;
        }
    }

    public MilkyPlayer getMilkyPlayerByUuid(String uuid) {
        return Optional.ofNullable(restProvider.getMilkyPlayer(uuid).getBody())
                .orElseThrow(() -> new PlayerNotFoundRuntimeException("Player not found", uuid));
    }

    public MilkyPlayer getMilkyPlayerByUuid(UUID uuid) {
        return getMilkyPlayerByUuid(uuid.toString());
    }

    public MilkyPlayer saveMilkyPlayer(MilkyPlayer milkyPlayer) {
        HttpResponse<MilkyPlayer> response = restProvider.saveMilkyPlayer(milkyPlayer);
        if(!response.isSuccess()) {
            throw new APIRuntimeException("Failed API response.");
        } else {
            return milkyPlayer;
        }
    }

    public Optional<MilkyPlayer> saveMilkyPlayerOptional(MilkyPlayer milkyPlayer) {
        return Optional.ofNullable(saveMilkyPlayer(milkyPlayer));
    }

    public Optional<MilkyPlayer> setPlayerChatFormat(MilkyPlayer player, ChatFormat chatFormat) {
        return setPlayerChatFormat(player.uid(), chatFormat);
    }

    public String getPlayerNickname(String uuid) {
        return getMilkyPlayerByUuid(uuid).nickname();
    }

    public Optional<MilkyPlayer> setPlayerNickname(String uuid, String nickname) {
        return saveMilkyPlayerOptional(getMilkyPlayerByUuid(uuid).nickname(nickname));
    }

    public Optional<MilkyPlayer> setPlayerChatFormat(String uuid, ChatFormat chatFormat) {
        return setPlayerChatFormat(uuid, chatFormat);
    }

    public Optional<MilkyPlayer> setPlayerChatFormat(String uuid, String chatFormat) {
        MilkyPlayer player = getMilkyPlayerByUuid(uuid);
        player.chatFormat(chatFormat);
        return saveMilkyPlayerOptional(player);
    }

    public ChatFormat getPlayerChatFormat(String uuid) {
        MilkyPlayer player = getMilkyPlayerByUuid(uuid);
        return getChatFormat(player.chatFormat());
    }

    public Optional<MilkyPlayer> setPlayerJLFormat(String uuid, JoinLeaveFormat joinLeaveFormat) {
        return setPlayerJLFormat(uuid, joinLeaveFormat.uid);
    }

    public Optional<MilkyPlayer> setPlayerJLFormat(String uuid, String joinLeaveFormat) {
        return saveMilkyPlayerOptional(getMilkyPlayerByUuid(uuid).joinLeaveFormat(joinLeaveFormat));
    }

    public JoinLeaveFormat getPlayerJLFormat(String uuid) {
        return getJLFormat(getMilkyPlayerByUuid(uuid).joinLeaveFormat());
    }

    public Optional<MilkyPlayer> addBalance(String uuid, Double add) {
        MilkyPlayer player = getMilkyPlayerByUuid(uuid);
        Double balance = player.balance();
        player.balance(balance + add);
        return saveMilkyPlayerOptional(player);
    }

    public Optional<MilkyPlayer> subtractBalance(String uuid, Double subtract) {
        MilkyPlayer player = getMilkyPlayerByUuid(uuid);
        Double balance = player.balance();
        player.balance(balance - subtract);
        return saveMilkyPlayerOptional(player);
    }

    public Double getBalance(String uuid) {
        MilkyPlayer player = getMilkyPlayerByUuid(uuid);
        return player.balance();
    }

    public Optional<MilkyPlayer> addVotes(String uuid, Integer add) {
        MilkyPlayer player = getMilkyPlayerByUuid(uuid);
        Integer votes = player.votes();
        player.votes(votes + add);
        return saveMilkyPlayerOptional(player);
    }

    public Optional<MilkyPlayer> addVote(String uuid) {
        return addVotes(uuid, 1);
    }

    public Optional<MilkyPlayer> subtractVotes(String uuid, Integer subtract) {
        MilkyPlayer player = getMilkyPlayerByUuid(uuid);
        Integer votes = player.votes();
        player.votes(votes - subtract);
        return saveMilkyPlayerOptional(player);
    }

    public Integer getVotes(String uuid) {
        MilkyPlayer player = getMilkyPlayerByUuid(uuid);
        return player.votes();
    }

    public Optional<MilkyPlayer> addSecondsPlayed(String uuid, Integer add) {
        MilkyPlayer player = getMilkyPlayerByUuid(uuid);
        Integer played = player.secondsPlayed();
        player.secondsPlayed(played + add);
        return saveMilkyPlayerOptional(player);
    }

    public Integer getSecondsPlayed(String uuid) {
        return getMilkyPlayerByUuid(uuid).secondsPlayed();
    }

    public Long getJoinDate(String uuid) {
        return getMilkyPlayerByUuid(uuid).joinDate();
    }

    public Optional<MilkyPlayer> setLastLocation(String uuid, ServerLocation location) {
        return saveMilkyPlayerOptional(getMilkyPlayerByUuid(uuid).lastLocation(location));
    }

    public ServerLocation getLastLocation(String uuid) {
        return getMilkyPlayerByUuid(uuid).lastLocation();
    }

    public ChatFormat getChatFormat(String id) {
        HttpResponse<ChatFormat> format = restProvider.getChatFormat(id);
        if(!format.isSuccess()) {
            if(format.getStatus() == 404) {
                return getChatFormat("default");
            } else {
                throw new APIRuntimeException("Failed API response");
            }
        } else {
            return format.getBody();
        }
    }

    public JoinLeaveFormat getJLFormat(String id) {
        HttpResponse<JoinLeaveFormat> format = restProvider.getJoinLeaveFormat(id);
        if(!format.isSuccess()) {
            if(format.getStatus() == 404) {
                return getJLFormat("default");
            } else {
                throw new APIRuntimeException("Failed API response");
            }
        } else {
            return format.getBody();
        }
    }

    public ModelLoader modelLoader() {
        return modelLoader;
    }
}
