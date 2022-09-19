package com.project.gil.domain;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter @Setter
@NoArgsConstructor
public class QueueDTO {

    Wait wait;
    int count;

    public QueueDTO(Wait wait, int count) {
        this.wait = wait;
        this.count = count;
    }
}
