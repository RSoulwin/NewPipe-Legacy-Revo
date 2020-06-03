package org.schabi.newpipelegacy.database.playlist

import org.schabi.newpipelegacy.database.LocalItem
import org.schabi.newpipelegacy.database.playlist.model.PlaylistStreamEntity
import org.schabi.newpipelegacy.database.stream.model.StreamEntity
import org.schabi.newpipe.extractor.stream.StreamInfoItem

import androidx.room.ColumnInfo
import androidx.room.Embedded

class PlaylistStreamEntry(
    @Embedded
    val streamEntity: StreamEntity,

    @ColumnInfo(name = PlaylistStreamEntity.JOIN_STREAM_ID)
    val streamId: Long,

    @ColumnInfo(name = PlaylistStreamEntity.JOIN_INDEX)
    val joinIndex: Int
) : LocalItem {

    @Throws(IllegalArgumentException::class)
    fun toStreamInfoItem(): StreamInfoItem {
        val item = StreamInfoItem(streamEntity.serviceId, streamEntity.url, streamEntity.title, streamEntity.streamType)
        item.duration = streamEntity.duration
        item.uploaderName = streamEntity.uploader
        item.thumbnailUrl = streamEntity.thumbnailUrl

        return item
    }

    override fun getLocalItemType(): LocalItem.LocalItemType {
        return LocalItem.LocalItemType.PLAYLIST_STREAM_ITEM
    }
}
