package de.romjaki.messagedeleter;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.requests.RestAction;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import static de.romjaki.messagedeleter.Main.jda;
import static de.romjaki.messagedeleter.Main.timer;
import static de.romjaki.messagedeleter.Util.batches;

public class CreationListener extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event) {
        super.onReady(event);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                TextChannel channel = jda.getTextChannelById(Config.getInstance().channel);
                OffsetDateTime terminator = OffsetDateTime.now().minusDays(2);
                OffsetDateTime twoWeeks = OffsetDateTime.now().minusDays(13);
                List<Message> toDelete = new ArrayList<>();
                for (Message m : channel.getIterableHistory()) {
                    if (m.getCreationTime().compareTo(terminator) < 0) {
                        toDelete.add(m);
                    }
                }
                System.out.printf("Scheduling %d messages for deletion%n", toDelete.size());
                toDelete.stream()
                        .filter(message ->
                                message.getCreationTime()
                                        .compareTo(twoWeeks) < 0)
                        .map(Message::delete)
                        .forEach(RestAction::queue);
                batches(
                        toDelete.stream()
                                .filter(message ->
                                        message.getCreationTime()
                                                .compareTo(twoWeeks) >= 0),
                        100)
                        .map(channel::deleteMessages)
                        .forEach(RestAction::queue);

            }
        }, 0, 60000);

    }
}
