package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    private String id;
    private String imageB64;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        return imageB64.equals(image.imageB64);
    }

    @Override
    public int hashCode() {
        return imageB64.hashCode();
    }
}
