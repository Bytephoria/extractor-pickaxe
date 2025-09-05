package team.bytephoria.extractorpickaxe.data;

import org.jetbrains.annotations.NotNull;

/**
 * Holds cached messages loaded from the configuration file.
 * Each field corresponds to a path under the "messages" section.
 */
public final class CacheData {

    private String noPermission;
    private String invalidCommand;
    private String extractorReceived;
    private String convertSuccess;
    private String convertFail;
    private String invalidNumber;
    private String setUsesSuccess;
    private String setUsesFail;
    private String mustHoldPickaxe;

    public String getNoPermission() {
        return this.noPermission;
    }

    public CacheData setNoPermission(final @NotNull String noPermission) {
        this.noPermission = noPermission;
        return this;
    }

    public @NotNull String getInvalidCommand() {
        return this.invalidCommand;
    }

    public CacheData setInvalidCommand(final @NotNull String invalidCommand) {
        this.invalidCommand = invalidCommand;
        return this;
    }

    public @NotNull String getExtractorReceived() {
        return this.extractorReceived;
    }

    public CacheData setExtractorReceived(final @NotNull String extractorReceived) {
        this.extractorReceived = extractorReceived;
        return this;
    }

    public @NotNull String getConvertSuccess() {
        return this.convertSuccess;
    }

    public CacheData setConvertSuccess(final @NotNull String convertSuccess) {
        this.convertSuccess = convertSuccess;
        return this;
    }

    public @NotNull String getConvertFail() {
        return this.convertFail;
    }

    public CacheData setConvertFail(final @NotNull String convertFail) {
        this.convertFail = convertFail;
        return this;
    }

    public @NotNull String getInvalidNumber() {
        return this.invalidNumber;
    }

    public CacheData setInvalidNumber(final @NotNull String invalidNumber) {
        this.invalidNumber = invalidNumber;
        return this;
    }

    public @NotNull String getSetUsesSuccess() {
        return this.setUsesSuccess;
    }

    public CacheData setSetUsesSuccess(final @NotNull String setUsesSuccess) {
        this.setUsesSuccess = setUsesSuccess;
        return this;
    }

    public @NotNull String getSetUsesFail() {
        return this.setUsesFail;
    }

    public CacheData setSetUsesFail(final @NotNull String setUsesFail) {
        this.setUsesFail = setUsesFail;
        return this;
    }

    public String getMustHoldPickaxe() {
        return this.mustHoldPickaxe;
    }

    public CacheData setMustHoldPickaxe(final @NotNull String mustHoldPickaxe) {
        this.mustHoldPickaxe = mustHoldPickaxe;
        return this;
    }
}