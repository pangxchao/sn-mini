package com.mini.core.jdbc.listener;

import com.mini.core.jdbc.MiniRepository;
import com.mini.core.util.holder.FieldHolder;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.event.EventListener;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.conversion.DbAction;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

import static com.mini.core.util.PKGenerator.id;
import static com.mini.core.util.PKGenerator.uuid;
import static com.mini.core.util.holder.ClassHolder.create;

@Component
public class MiniGeneratedIdListener {

    @EventListener
    @SuppressWarnings("DuplicatedCode")
    public void beforeSaveEventHandler(@NotNull BeforeSaveEvent<?> beforeSaveEvent) {
        beforeSaveEvent.getAggregateChange().forEachAction(action -> {
            if (!(action instanceof DbAction.WithGeneratedId<?>)) {
                return;
            }
            Object entity = ((DbAction.WithGeneratedId<?>) action).getEntity();
            create(action.getEntityType()).getFields().values().forEach(field -> {
                Id id = field.getAnnotation(Id.class);
                if (id == null) return;

                if (String.class == field.getType()) {
                    field.setValue(entity, uuid());
                    return;
                }
                if (Long.class == field.getType()) {
                    field.setValue(entity, id());
                    return;
                }
                if (long.class == field.getType()) {
                    field.setValue(entity, id());
                }
            });
        });
    }

    @EventListener
    @SuppressWarnings("DuplicatedCode")
    public void miniGeneratedIdHandler(MiniRepository.MiniId miniId) {
        final java.lang.Object entity = miniId.getSource();
        final FieldHolder<?> field = miniId.getField();
        if (field.getValue(entity) != null) return;

        if (String.class == field.getType()) {
            field.setValue(entity, uuid());
            return;
        }
        if (Long.class == field.getType()) {
            field.setValue(entity, id());
            return;
        }
        if (long.class == field.getType()) {
            field.setValue(entity, id());
        }
    }
}
