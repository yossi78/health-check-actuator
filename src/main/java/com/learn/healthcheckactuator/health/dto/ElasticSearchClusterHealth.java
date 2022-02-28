package com.redbend.telemery.health.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class ElasticSearchClusterHealth {

    private String cluster_name;
    private String status;
    private Boolean timed_out;
    private Integer number_of_nodes;
    private Integer number_of_data_nodes;
    private Integer active_primary_shards;
    private Integer active_shards;
    private Integer relocating_shards;
    private Integer initializing_shards;
    private Integer unassigned_shards;
    private Integer delayed_unassigned_shards;
    private Integer number_of_pending_tasks;
    private Integer number_of_in_flight_fetch;
    private Integer task_max_waiting_in_queue_millis;
    private Double active_shards_percent_as_number;
    
}
