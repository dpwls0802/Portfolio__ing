package com.record.travel.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTravelrecord is a Querydsl query type for Travelrecord
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTravelrecord extends EntityPathBase<Travelrecord> {

    private static final long serialVersionUID = -383323662L;

    public static final QTravelrecord travelrecord = new QTravelrecord("travelrecord");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath title = createString("title");

    public final NumberPath<Long> tnum = createNumber("tnum", Long.class);

    public final StringPath travelDate = createString("travelDate");

    public final StringPath writer = createString("writer");

    public QTravelrecord(String variable) {
        super(Travelrecord.class, forVariable(variable));
    }

    public QTravelrecord(Path<? extends Travelrecord> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTravelrecord(PathMetadata metadata) {
        super(Travelrecord.class, metadata);
    }

}

